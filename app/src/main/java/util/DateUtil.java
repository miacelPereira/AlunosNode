package util;

public class DateUtil {

    public int convertToInt(String data){

        String [] dataSplit = data.split("/");
        String dia = dataSplit[0];
        String mes = dataSplit[1];
        String ano = dataSplit[2];

        String dataFormada = ano + mes + dia;
        return Integer.parseInt(dataFormada);
    }

}
