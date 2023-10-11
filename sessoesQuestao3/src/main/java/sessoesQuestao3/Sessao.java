package sessoesQuestao3;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Sessao")
public class Sessao extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	HttpSession sessao = request.getSession(true);
            Integer acessos = (Integer) sessao.getAttribute("Numero_acessos");
            if (acessos == null) {
                acessos = 1;
            } else {
                acessos = acessos + 1;
            }
            
            sessao.setAttribute("Numero_acessos", acessos);
            PrintWriter writer = response.getWriter();            
            
            writer.append(" <HTML>  <BODY> ");
            
            String sessaoId = sessao.getId();
            writer.append("ID da Sessão: " + sessaoId + "<br>");
            
            Date dataCriacao = new Date(sessao.getCreationTime());
            SimpleDateFormat FormatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            writer.append("Data e Hora de Criação da Sessão: " + FormatoData.format(dataCriacao) + "<br>");
            
            Date UltimoAcesso = new Date(sessao.getLastAccessedTime());
            writer.append("Data e Hora do Último Acesso à Sessão: " + FormatoData.format(UltimoAcesso) + "<br>");
            
            writer.append("O documento foi acessado " + acessos + " vezes.");
            
            if (acessos == 5) {
                writer.append("<br>Você alcançou 5 acessos!!");
            }
            
            writer.append("</BODY> </HTML>  ");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
