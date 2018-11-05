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
    public String convertToString(int data){
        String dataFormatada;
        String dataAux = Integer.toString(data);
        String ano = dataAux.substring(0,4);
        String mes = dataAux.substring(4,6);
        String dia = dataAux.substring(6,8);
        dataFormatada = dia+"/"+mes+"/"+ano;
    return dataFormatada;
    }
}
