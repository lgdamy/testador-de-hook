package com.damytec.testadordehook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author lgdamy@raiadrogasil.com on 13/11/2020
 */
@RestController
@RequestMapping("/seek")
public class HookSeekController {
    @Autowired
    private HookSaverService service;

    @GetMapping
    public String hookSeek() {
        List<HookDTO> hooks = service.buscarHooks();
        StringBuilder sb =
                new StringBuilder("<html><body><style type=\"text/css\">\n" +
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
                        "    <th class=\"tg-0lax\">M�todo</th>\n" +
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
