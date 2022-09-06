import org.mariuszgromada.math.mxparser.*;
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyClass {

    public double ajustarCasas(double num, int casas) {
    BigDecimal bd = new BigDecimal(num).setScale(casas, RoundingMode.HALF_EVEN);
    return bd.doubleValue();
    }

    public static double iteracao(String equacao, double eq_iteracao){
        Function f = new Function("f(x)="+equacao);
        Argument x = new Argument("x="+eq_iteracao);
        Expression e2 = new Expression("f(x)",f,x);
        return (float) e2.calculate();
    }
    public static float calculaErro(String erro){
        Function f = new Function("f(x)=x");
        Argument x = new Argument("x="+erro);
        Expression e2 = new Expression("f(x)",f,x);
        return (float) e2.calculate();
    }

    public static void main(String[] args) {
        MyClass metodo = new MyClass();
        Scanner teclado = new Scanner(System.in);
        String equacao,equacao_iterativa="",errotemp;
        int qtd = -1;
        float erro;
        double p1 = 0,p2 = 0;
        double eq_iteracao = 0,eq_original=0;
        int op;
        String eq_erro;

        System.out.println("Obs.: Favor, utilizar ponto ao invés de vírgula ao informar as equações!+");
        System.out.println("- Método do ponto fixo -");
        System.out.println("Digite a equação: ");
        equacao = teclado.nextLine();
        System.out.println("Digite a equação de iteração: ");
        equacao_iterativa = teclado.nextLine();
        System.out.println("Digite o erro esperado: ");
        //erro = teclado.nextFloat();
        eq_erro = teclado.nextLine();
        erro = calculaErro(eq_erro);
        System.out.println("--------------------Intervalo--------------------");
        System.out.println("- 1 - Informar intervalo                        -");
        System.out.println("- 2 - Informar X0                               -");
        System.out.println("-------------------------------------------------");
        System.out.println("-> Opção desejada: ");
        op = teclado.nextInt();
        if(op == 1){
        System.out.println("Digite o primeiro ponto do intervalo: ");
        p1 = teclado.nextDouble();
        System.out.println("Digite o segundo ponto do intervalo: ");
        p2 = teclado.nextDouble();
        eq_iteracao =  (p1+p2) / 2;
        }
        if (op==2) {
            System.out.println("Digite x0: ");
            eq_iteracao = teclado.nextFloat();
        }
        System.out.println("--------------------------------------------------");
        System.out.println("- Fi de x               |                   Iteração -");
        System.out.println("--------------------------------------------------");
        eq_original = iteracao(equacao,eq_iteracao);
        System.out.printf("- %.4f               |                 %.4f -",eq_iteracao,eq_original);
        System.out.println();
        int conf=1;
        while(conf == 1){
            if(eq_original<0){
                eq_original = eq_original * -1;
            }
            if((eq_original<erro)) {
                qtd++;
                eq_iteracao = metodo.ajustarCasas(eq_iteracao,5);
                System.out.println("--------------------------------------------------");
                System.out.println("Foi encontrada a raiz " + eq_iteracao + " com " + qtd + " iterações.");
                conf = 0;
            } else {
                if(eq_iteracao>9999) break;
                eq_iteracao = iteracao(equacao_iterativa, eq_iteracao);
                eq_original = iteracao(equacao, eq_iteracao);
                System.out.printf("- %.5f               |                 %.5f -",eq_iteracao,eq_original);
                System.out.println();
                qtd++;
            }
        }
    }
}