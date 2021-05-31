package bruno.estacionamentoAPI.util;


import org.json.JSONObject;

public class RespostaJson {

  public static String mensagem(String mensagem) {
    return new JSONObject()
            .put("mensagem", mensagem)
            .toString();
  }
}
