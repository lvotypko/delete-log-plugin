package jenkinsci.plugin.deletelog;

import hudson.matrix.MatrixBuild;
import hudson.model.Action;
import hudson.model.Run;
import java.io.IOException;
import javax.servlet.ServletException;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

/**
 * Delete all logs of build
 * 
 * @author Lucie Votypkova
 */
public class LogDelete implements Action{
    
    private Run build;
    
    public LogDelete(Run build){
        this.build=build;
    }

    public String getIconFileName() {
        return "edit-delete.png";
    }

    public String getDisplayName() {
        return "Delete log";
    }

    public String getUrlName() {
        return "delete-log";
    } 
    
    public Run getBuild(){
        return build;
    }
       
    public void doDelete(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {
        if(build.isBuilding()){
            req.getView(this, "error.jelly");
        }
        String submit = req.getParameter("Submit");
        if("Yes".equals(submit)){
            build.getLogFile().delete();
            if(build instanceof MatrixBuild){
                MatrixBuild matrixBuild = (MatrixBuild) build;
               for(Run run : matrixBuild.getRuns()){
                   if(run.getLogFile().exists())
                       run.getLogFile().delete();
               }
            }
        }
        rsp.sendRedirect2("../");
    }

    
}