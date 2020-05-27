package controller.helper;

import java.util.ArrayList;

public class Validation {
    
    private boolean validated;
    private ArrayList<String> annotations = new ArrayList();
    
    public Validation(){  
    }     
    
    public Validation(boolean validated){
        this.validated = validated;  
    }   
      
    public Validation(boolean validated,ArrayList annotations){
        this.validated = validated;
        this.annotations = annotations;   
    }
    
    public void addAnnotation(String annotation){
        annotations.add(annotation);
    }

    public boolean isValidated() {
        return validated;
    }

    public ArrayList<String> getAnnotations() {
        return annotations;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public void setAnnotations(ArrayList<String> annotations) {
        this.annotations = annotations;
    }
    
    public void setValidation(boolean validated, ArrayList<String> annotations) {
        this.validated = validated;
        this.annotations = annotations;
    }

    @Override
    public String toString() {
        return "Validation{" + "validated=" + validated + ", annotations=" + annotations + '}';
    }
      
}
