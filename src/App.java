import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String[][] doencas = {
            // {"virose comum", "Você está com febre e fadiga muscular?", "Você está tossindo?", "-"},
            {"covid-19", "Você está com febre e fadiga muscular?", "Você está tossindo?", "Você perdeu o paladar ou olfato?"},
            {"dengue","Você está com febre e fadiga muscular?","Você está com dor atrás dos olhos?","Você está com manchas avermelhadas?"},
            {"febre amarela", "Você está com febre e fadiga muscular?", "Você está com pele e/ou olhos amarelados?", "-"},
            {"raiva", "Você está com febre e fadiga muscular?", "Você teve paralisia ou espamos musculares com certa frequência?", "Você sente que seu comportamento está mais agressivo, irritado?"},
            {"herpes", "Você tem bolhas, erupções e úlceras na pele?", "Seus lábios estão formigamento e doendo?", "-"}
        };

        int[][] respostas = {
            {1,0,0,0},
            {1,0,0,0},
            {1,0,0,1},
            {1,0,0,0},
            {1,0,0,1}
        };

        int somaDasLinhas = 0;
        int linhaDescoberta = 0;
        boolean descartarDoenca;
            for(int linhaDoencas = 0; linhaDoencas < doencas.length; linhaDoencas++){           //Loops para entrar na linha das doenças 
                for(int colunaDoencas = 0; colunaDoencas < doencas[0].length; colunaDoencas++){ //e perguntar as colunas dos sintomas

                    //Estrutura que verifica se ainda tem possibilidades de ser a doença atual. Se tiver alguma resposta "não", pule para a próxima
                    descartarDoenca = false;
                    for(int j = 0; j < doencas[0].length; j++){
                        if(respostas[linhaDoencas][j] == -1){
                            descartarDoenca = true;
                            break;
                        }
                    }
                    if(descartarDoenca)
                        break;

                    if(respostas[linhaDoencas][colunaDoencas] == 0){ //Se a pergunta que estamos ainda não foi respondida. Caso contrário, pule para a próxima
                        System.out.println(doencas[linhaDoencas][colunaDoencas] + " (responda s/n)");
                        Scanner scan = new Scanner(System.in);
                        int resposta = scan.nextLine().equals("s") ? 1 : -1; 
                        
                        //Estrutura que cadastra nossa resposta em todas as perguntas iguais na matriz de respostas para não repetir perguntas depois    
                        for(int i = 0; i < doencas.length; i++){ 
                            for(int j = 0; j < doencas[0].length; j++){
                                if(doencas[i][j].equals(doencas[linhaDoencas][colunaDoencas]))
                                    respostas[i][j] = resposta;
                            }
                        }

                        //Estrutura para verificar se já diagnosticamos alguma doença toda vez que respondermos "sim" para algum sintoma
                        for(int linhaRespostas = 0; linhaRespostas < respostas.length; linhaRespostas++){ 
                            somaDasLinhas = 0;
                            for(int colunaRespostas = 0; colunaRespostas < respostas[0].length; colunaRespostas++){
                                if(respostas[linhaRespostas][colunaRespostas] == 1)
                                    somaDasLinhas++;
                                if(somaDasLinhas == respostas[0].length){
                                    linhaDescoberta = linhaRespostas;
                                    break;
                                }
                            }
                            if(somaDasLinhas == respostas[0].length){break;}
                        }
                    }
                    if(somaDasLinhas == respostas[0].length){break;}
                }
                if(somaDasLinhas == respostas[0].length){break;}
            }
        System.out.print("Possivelmente, você está com " + doencas[linhaDescoberta][0]);
    }
}
