/*
 * This file is part of adventure-text-minimessage, licensed under the MIT License.
 *
 * Copyright (c) 2018-2020 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.adventure.text.minimessage.parser.node;

import net.kyori.adventure.text.minimessage.parser.Token;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class TextNode extends ElementNode {

  private final String value;

  public TextNode(final ElementNode parent, final Token token, final String sourceMessage) {
    super(parent, token, sourceMessage);
    this.value = sourceMessage.substring(token.startIndex(), token.endIndex()).replace("\\", "");
  }

  public String value() {
    return this.value;
  }

  public @NonNull StringBuilder buildToString(final @NonNull StringBuilder sb, final int indent) {
    final char[] in = this.getIndent(indent);
    sb.append(in).append("TextNode('").append(this.value).append("')\n");
    return sb;
  }
}