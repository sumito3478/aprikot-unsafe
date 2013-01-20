/* Copyright (C) 2013 sumito3478 <sumito3478@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package info.sumito3478.aprikot.unsafe

import com.sun.jna.Native

/**
 * A trait represents a memory block.
 *
 * Use Memory(Long) to allocate the memory block with the default
 * implementation.
 *
 * Example Usage:
 * {{{
 * import info.sumito3478.aprikot.io.{Memory, using, le}
 *
 * using(Memory(4)) {
 *   block =>
 *     var p = block.pointer
 *     p.int(le(0xcafebabe))
 *     println(f"0x${p.byte}") // => 0xbe
 *     p += 1
 *     println(f"0x${p.byte}") // => 0xba
 *     p += 1
 *     println(f"0x${p.byte}") // => 0xfe
 *     p += 1
 *     println(f"0x${p.byte}") // => 0xca
 * }
 * }}}
 */
trait Memory extends Disposable {
  /**
   * Get the pointer that points to the beginning of the memory block.
   */
  def pointer: Pointer
}

object Memory {
  /**
   * Allocates a memory block with the given size and return the instance of
   * [[Memory]] that references to that block.
   *
   * @note Current implementation allocates the memory with
   *   [[com.sun.jna.Native.malloc]] and deallocates it with
   *   [[com.sun.jna.Native.free]].
   */
  def apply(size: Long): Memory = {
    val ptr = Native.malloc(size)
    new Memory {
      def pointer: Pointer = {
        //Pointer(ptr)
        new Pointer(ptr)
      }

      def disposeInternal: Unit = {
        Native.free(ptr)
      }

      override def finalize: Unit = {
        dispose
      }
    }
  }
}