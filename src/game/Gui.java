package game;

import game.util.MainManager;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;

public class Gui {
    
    public static void setWidth(String name, String value){
        MainManager.nifty.getCurrentScreen().findElementByName(name).setConstraintWidth(new SizeValue(value));
        
    }
    
    public static void setText(String name, String value){
        MainManager.nifty.getCurrentScreen().findElementByName(name).getRenderer(TextRenderer.class).setText(value);
    }
    
}
