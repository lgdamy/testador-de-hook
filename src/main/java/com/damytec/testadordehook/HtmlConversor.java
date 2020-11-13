package com.damytec.testadordehook;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Preguiça de usar o thymeleaf pra essa porcaria
 * @author lgdamy@raiadrogasil.com on 13/11/2020
 */
public class HtmlConversor {

    public static String converter(List<HookDTO> hooks) {
        StringBuilder sb =
                new StringBuilder("<html>" +
                        "<head>\n" +
                        "  <title>Paumolecencia</title>\n" +
                        "</head>" +
                        "<body><style type=\"text/css\">\n" +
                        ".tg  {border-collapse:collapse;border-spacing:0;}\n" +
                        ".tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
                        "  overflow:hidden;padding:10px 5px;word-break:normal;}\n" +
                        ".tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
                        "  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}\n" +
                        ".tg .tg-0lax{text-align:left;vertical-align:top}\n" +
                        "</style>\n" +
                        "<table class=\"tg\">\n" +
                        "<thead>\n" +
                        "  <tr>\n" +
                        "    <th class=\"tg-0lax\">URL</th>\n" +
                        "    <th class=\"tg-0lax\">Metodo</th>\n" +
                        "    <th class=\"tg-0lax\">Body</th>\n" +
                        "    <th class=\"tg-0lax\">Headers</th>\n" +
                        "    <th class=\"tg-0lax\">Origem</th>\n" +
                        "    <th class=\"tg-0lax\">Hora</th>\n" +
                        "  </tr>\n" +
                        "</thead>\n");

        if (hooks != null && !hooks.isEmpty()) {
            sb.append("<tbody>\n");
            for (HookDTO hook : hooks) {
                sb.append("  <tr>\n" +
                        "    <td class=\"tg-0lax\">"+ hook.getDestino() + "</td>\n" +
                        "    <td class=\"tg-0lax\">"+ hook.getMetodo()+"</td>\n" +
                        "    <td class=\"tg-0lax\">" + hook.getBody() + "</td>\n" +
                        "    <td class=\"tg-0lax\">"+hook.getHeaders().toString() +"</td>\n" +
                        "    <td class=\"tg-0lax\">"+ hook.getOrigem() +"</td>\n" +
                        "    <td class=\"tg-0lax\">"+ new SimpleDateFormat("dd/MM/YYYY - HH:mm:ss").format(hook.getHora()) +"</td>\n" +
                        "  </tr>\n");
            }
            sb.append("</tbody>\n");
        }
        sb.append("</table></body></html>");
        return sb.toString();
    }
}
