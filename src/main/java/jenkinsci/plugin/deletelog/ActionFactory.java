package jenkinsci.plugin.deletelog;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.Action;

import hudson.model.Run;
import hudson.model.TransientBuildActionFactory;
import java.util.ArrayList;
import java.util.Collection;


/**
 *Add delete log action into a build page
 * 
 * 
 * @author Lucie Votypkova
 */
@Extension
public class ActionFactory extends TransientBuildActionFactory{
    
    @Override
    public Collection<? extends Action> createFor(Run target) {
        ArrayList<Action> actions = new ArrayList<Action>();
        if(target.getLogFile().exists() && (!target.isBuilding())){
        LogDelete newAction = new LogDelete(target);
        actions.add(newAction); 
        }
        return actions;
        
    }
    
}

