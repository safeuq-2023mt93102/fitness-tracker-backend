// Copyright (c) 2022 - Safeuq Mohamed, mohamedsafeuq@outlook.com
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.bits.group13.fitnesstracker.model.jackson;

import com.bits.group13.fitnesstracker.model.EnumSerializable;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

/**
 * @author Safeuq Mohamed
 */
public class EnumSerializer extends StdSerializer<EnumSerializable> {
  protected EnumSerializer(Class<EnumSerializable> enumSerializableClass) {
    super(enumSerializableClass);
  }

  @Override
  public void serialize(
      EnumSerializable enumSerializable, JsonGenerator generator, SerializerProvider provider)
      throws IOException {}
}
