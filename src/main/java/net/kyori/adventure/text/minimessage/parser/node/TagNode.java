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

import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.minimessage.ParseException;
import net.kyori.adventure.text.minimessage.parser.Token;
import org.checkerframework.checker.nullness.qual.NonNull;

public class TagNode extends ElementNode {

  private final List<TagPart> parts;

  public TagNode(final ElementNode parent, final Token token, final String sourceMessage) {
    super(parent, token, sourceMessage);
    this.parts = genParts(token, sourceMessage);
  }

  private static List<TagPart> genParts(final Token token, final String sourceMessage) {
    final ArrayList<TagPart> parts = new ArrayList<>();

    if(token.childTokens() != null) {
      for(final Token childToken : token.childTokens()) {
        parts.add(new TagPart(sourceMessage, childToken));
      }
    }

    return parts;
  }

  public List<TagPart> parts() {
    return this.parts;
  }

  public String name() {
    if(this.parts.isEmpty()) {
      throw new ParseException("Tag has no parts? " + this);
    }
    return this.parts.get(0).value();
  }

  public @NonNull StringBuilder buildToString(final @NonNull StringBuilder sb, final int indent) {
    final char[] in = this.getIndent(indent);
    sb.append(in).append("TagNode(");

    final int size = this.parts.size();
    for(int i = 0; i < size; i++) {
      final TagPart part = this.parts.get(i);
      sb.append('\'').append(part.value()).append('\'');
      if(i != size - 1) {
        sb.append(", ");
      }
    }

    sb.append(") {\n");

    for(final ElementNode child : this.children()) {
      child.buildToString(sb, indent + 1);
    }
    sb.append(in).append("}\n");
    return sb;
  }
}