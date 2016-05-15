
uniform sampler2D m_FirstTexture;
uniform sampler2D m_SecondTexture;
uniform sampler2D m_MaskTexture;

varying vec4 texCoord;

void main() {
    vec4 baseColor = vec4(1.0, 0.0, 1.0, 0.0);

    baseColor = mix(baseColor, texture2D(m_FirstTexture, texCoord.xy), texture2D(m_MaskTexture, texCoord.xy));

    gl_FragColor = baseColor;
    //mix(texture2D(m_FirstTexture, texCoord.xy), texture2D(m_SecondTexture,texCoord.xy), texture2D(m_MaskTexture, texCoord.xy));
}