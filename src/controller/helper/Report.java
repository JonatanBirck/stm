package controller.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import model.database.ConnectionDB;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Report {
        
    private JasperReport report;
    private final Map param = new HashMap();    
         
    public void report1(){
        try
        {
            report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/view/reports/usuariosDepartamento.jrxml"));
            JasperPrint print = JasperFillManager.fillReport(report,param, ConnectionDB.getInstance().getConnection());
            JasperViewer.viewReport(print, false);
        }
        catch (JRException e)
        {
            System.out.println("[ERRO] Relátorio");
        }
    }
    
    public void report2(){
        try
        {
            report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/view/reports/vinculoUsuario.jrxml"));
            JasperPrint print = JasperFillManager.fillReport(report,param, ConnectionDB.getInstance().getConnection());
            JasperViewer.viewReport(print, false);
        }
        catch (JRException e)
        {
            System.out.println("[ERRO] Relátorio");
        }        
    }
    
    public void report3(int responsability, String startDate, String endDate){
        try
        {
            report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/view/reports/tarefasResponsavel.jrxml"));
            SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date dataInicio = new java.sql.Date(formatData.parse(startDate).getTime());
            java.sql.Date dataFim = new java.sql.Date(formatData.parse(endDate).getTime());
                    
            param.put("responsavel", responsability);
            param.put("dataInicio", dataInicio);
            param.put("dataFim", dataFim);
            JasperPrint print = JasperFillManager.fillReport(report,param, ConnectionDB.getInstance().getConnection());
            JasperViewer.viewReport(print, false);
        }
        catch (ParseException | JRException e)
        {
            System.out.println("[ERRO] Relátorio");
        }        
    }    
    
    public void report4(int stateTask, String startDate, String endDate){
        try
        {
            report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/view/reports/situacaoTarefas.jrxml"));
            SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date dataInicio = new java.sql.Date(formatData.parse(startDate).getTime());
            java.sql.Date dataFim = new java.sql.Date(formatData.parse(endDate).getTime());
                    
            param.put("estado", stateTask);
            param.put("dataInicio", dataInicio);
            param.put("dataFinal", dataFim);
            JasperPrint print = JasperFillManager.fillReport(report,param, ConnectionDB.getInstance().getConnection());
            JasperViewer.viewReport(print, false);
        }
        catch (ParseException | JRException e)
        {
            System.out.println("[ERRO] Relátorio");
        }        
    }     
    
}
