
uniform sampler2D m_WaterTexture;
uniform sampler2D m_StoneTexture;
uniform sampler2D m_SandTexture;
uniform sampler2D m_DirtTexture;
uniform sampler2D m_GrassTexture;
uniform sampler2D m_Mask;

varying vec4 texCoord;

void main() {
    vec4 baseColor = vec4(texture2D(m_WaterTexture, texCoord.xy));

    baseColor = mix(baseColor, texture2D(m_StoneTexture, texCoord.xy), texture2D(m_Mask, texCoord.xy).r);
    baseColor = mix(baseColor, texture2D(m_SandTexture, texCoord.xy), texture2D(m_Mask, texCoord.xy).g);
    baseColor = mix(baseColor, texture2D(m_DirtTexture, texCoord.xy), texture2D(m_Mask, texCoord.xy).b);
    baseColor = mix(baseColor, texture2D(m_GrassTexture, texCoord.xy), texture2D(m_Mask, texCoord.xy).a);

    //baseColor = vec4(texture2D(m_Mask, texCoord.xy).a);

    gl_FragColor = baseColor;
}