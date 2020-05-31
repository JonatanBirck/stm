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
    
    //Ex: 10/05/2020
    public boolean dateIsValid(String date){
        
        if(!date.contains("/") || date.contains(" ") || date.length() < 9)
        {
            return false;
        }
        
        String[] data = date.split("/");
        
        if(data[0].isEmpty() || data[1].isEmpty() || data[2].isEmpty())
        {
            return false;
        }
        
        int d = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);
        int a = Integer.parseInt(data[2]);
                
        boolean correto = true;
        
        int[] dias = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (a < 0 || m < 1 || m > 12) {
            correto = false;
        } else {
            // valida o dia
            if (a % 4 == 0 && (a % 100 != 0 || a % 400 == 0)) {
                dias[1] = 29;
            }
            if (d < 1 || d > dias[m - 1]) {
                correto = false;
            }
        }
        return (correto);
    }
      
}
