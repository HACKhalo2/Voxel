//
// The MIT License (MIT)
//
// Copyright (c) 2015 Guerra24
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//

#version 330 core

in vec3 position;
in vec2 textureCoords;
in vec3 normal;
in vec4 data;

out vec2 pass_textureCoords;
out vec3 surfaceNormal;
out vec4 pass_position;
out vec4 pass_Data;
out vec4 ShadowCoord;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 cameraPos;
uniform mat4 projectionLightMatrix;
uniform mat4 viewLightMatrix;
uniform mat4 biasMatrix;

void main() {
	vec3 pos = position - cameraPos;
	vec4 worldPosition = vec4(pos, 1.0);
	vec4 positionRelativeToCam = viewMatrix * worldPosition;
	gl_Position = projectionMatrix * positionRelativeToCam;
	pass_textureCoords = textureCoords;
	pass_position = vec4(position, 1.0);
	surfaceNormal = normal;
	pass_Data = data;
	
	vec4 posLight = viewLightMatrix * vec4(position, 1.0);
	vec4 a = projectionLightMatrix * posLight;
	ShadowCoord = biasMatrix * a;
}