#version 330 core

in vec3 color;
out vec4 outColor;

void main() {
    outColor = vec4(color.g - color.r, color.r - color.g, 1, 1);
}