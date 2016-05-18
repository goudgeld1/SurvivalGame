
uniform sampler2D m_StoneTexture;
uniform sampler2D m_GravelTexture;
uniform sampler2D m_GravelMask;
uniform sampler2D m_SandTexture;
uniform sampler2D m_SandMask;
uniform sampler2D m_DirtTexture;
uniform sampler2D m_DirtMask;
uniform sampler2D m_GrassTexture;
uniform sampler2D m_Mask;

varying vec4 texCoord;

void main() {
    vec4 baseColor = vec4(texture2D(m_StoneTexture, texCoord.xy));

    baseColor = mix(baseColor, texture2D(m_GravelTexture, texCoord.xy), texture2D(m_Mask, texCoord.xy).r);
    baseColor = mix(baseColor, texture2D(m_SandTexture, texCoord.xy), texture2D(m_Mask, texCoord.xy).g);
    baseColor = mix(baseColor, texture2D(m_DirtTexture, texCoord.xy), texture2D(m_Mask, texCoord.xy).b);
    baseColor = mix(baseColor, texture2D(m_GrassTexture, texCoord.xy), texture2D(m_Mask, texCoord.xy).a);

    //baseColor = vec4(texture2D(m_GrassMask, texCoord.xy).a);

    gl_FragColor = baseColor;
    //mix(texture2D(m_FirstTexture, texCoord.xy), texture2D(m_SecondTexture,texCoord.xy), texture2D(m_MaskTexture, texCoord.xy));
}