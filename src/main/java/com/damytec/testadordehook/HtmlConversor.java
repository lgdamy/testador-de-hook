package com.damytec.testadordehook;

import org.springframework.lang.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 * Preguiça de usar o thymeleaf pra essa porcaria
 *
 * @author lgdamy@raiadrogasil.com on 13/11/2020
 */
public class HtmlConversor {

    private static HtmlConversor INSTANCE;

    private SimpleDateFormat sdf;

    public static HtmlConversor getInstance() {
        return INSTANCE = INSTANCE != null ? INSTANCE : new HtmlConversor();
    }

    private HtmlConversor() {
        this.sdf = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-03"));
    }

    public String converter(List<HookDTO> hooks, String url, @Nullable String disabled) {
        String fire = url.replace("seek", "").replace("logs", "") + "fire";
        String exemplo = fire + "/exemplo/testado/123";
        StringBuilder sb = new StringBuilder();

        sb.append("<html>" +
                "<head>\n" +
                "  <title>Testador de Webhook</title>\n" +
                "</head>" +
                "<body><style type=\"text/css\">\n" +
                ".tg  {border-collapse:collapse;border-spacing:0;}\n" +
                ".tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
                "  overflow:hidden;padding:10px 5px;word-break:normal;}\n" +
                ".tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
                "  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}\n" +
                ".tg .tg-head{background-color:#efefef;text-align:left;vertical-align:top}\n" +
                ".tg .tg-body{text-align:left;vertical-align:top}\n" +
                "</style>\n" +
                "<p> Configure o hook para chamar: (" + fire + ") a rota \u00e9 aditiva, <a href=\"" + exemplo + "\">exemplo</a></p>");
        if (disabled != null) {
            sb.append("<p> Ocultando <a href=''>"+ disabled +"</a>");
        }
        sb.append("<table class=\"tg\">\n" +
                "<thead>\n" +
                "  <tr>\n" +
                ("URL".equalsIgnoreCase(disabled) ? "" : "<th class=\"tg-head\"><a href='?off=URL'>URL</a></th>\n") +
                ("Metodo".equalsIgnoreCase(disabled) ? "" : "    <th class=\"tg-head\"><a href='?off=Metodo'> Metodo</a></th>\n") +
                ("Body".equalsIgnoreCase(disabled) ? "" : "    <th class=\"tg-head\"><a href='?off=Body'>Body</a></th>\n") +
                ("Headers".equalsIgnoreCase(disabled) ? "" : "    <th class=\"tg-head\"><a href='?off=Headers'>Headers</a></th>\n") +
                ("Origem".equalsIgnoreCase(disabled) ? "" : "    <th class=\"tg-head\"><a href='?off=Origem'>Origem</a></th>\n") +
                ("Hora".equalsIgnoreCase(disabled) ? "" : "    <th class=\"tg-head\"><a href='?off=Hora'>Hora</a></th>\n") +
                "  </tr>\n" +
                "</thead>\n");

        if (hooks != null && !hooks.isEmpty()) {
            sb.append("<tbody>\n");
            for (HookDTO hook : hooks) {
                sb.append("  <tr>\n" +
                        ("URL".equalsIgnoreCase(disabled) ? "" : "    <td class=\"tg-body\">" + hook.getDestino() + "</td>\n") +
                        ("Metodo".equalsIgnoreCase(disabled) ? "" : "    <td class=\"tg-body\">" + hook.getMetodo() + "</td>\n") +
                        ("Body".equalsIgnoreCase(disabled) ? "" : "    <td class=\"tg-body\">" + hook.getBody() + "</td>\n") +
                        ("Headers".equalsIgnoreCase(disabled) ? "" : "    <td class=\"tg-body\">" + hook.getHeaders() + "</td>\n") +
                        ("Origem".equalsIgnoreCase(disabled) ? "" : "    <td class=\"tg-body\">" + hook.getOrigem() + "</td>\n") +
                        ("Hora".equalsIgnoreCase(disabled) ? "" : "    <td class=\"tg-body\">" + sdf.format(hook.getHora()) + "</td>\n") +
                        "  </tr>\n");
            }
            sb.append("</tbody>\n");
        }
        sb.append("</table></body></html>");
        sb.append("<footer>" +
                "<p>Autor: Luiz Guilherme Damy</p>" +
                "<p>C\u00f3digo fonte: <a href=\"https://github.com/lgdamy/testador-de-hook\">https://github.com/lgdamy/testador-de-hook</a></p>" +
                "</footer>");
        return sb.toString();
    }
}
