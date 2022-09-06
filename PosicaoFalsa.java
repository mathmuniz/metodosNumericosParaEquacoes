
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

public class MetodoPosicaoFalsa {
            
    public static void main(String[] args) {
        MetodoPosicaoFalsa metodo = new MetodoPosicaoFalsa();
        String equacao = "";
        double a, b, xk, moduloXk, erroRelativo = 0;
        int interacao = 0;
        Scanner teclado = new Scanner (System.in);
        
        System.out.println("----------------------------------------------------------\n"
                          +"------------------ INICIO PROGRAMA -----------------------\n"
                          +"----------------------------------------------------------");
        System.out.println();
        
        System.out.println("Informe uma equacao: ");
        equacao = teclado.next();
        
        System.out.println("Informe o ponto 'a' do intervalo [a,b]: ");
        a  = teclado.nextFloat();
        
        System.out.println("Informe o ponto 'b' do intervalo [a,b]: ");
        b = teclado.nextFloat();
        
        System.out.println("Informe o erro relativo: ");
        erroRelativo = teclado.nextDouble();
        
        if (funcao(equacao, a) * funcao(equacao, b) > 0) {
            System.out.println("Não há raiz nesse intervalo");
        } else {
            do {                
                xk = ((a*funcao(equacao, b) - b*funcao(equacao, a)) / (funcao(equacao, b) - funcao(equacao, a))); //media ponderada
                moduloXk = funcao(equacao, xk) *-1; // criterio de parada
                
                System.out.println("\ni: " +interacao);
                System.out.println("\na: " +a);
                System.out.println("\nb: " +b);
                System.out.println("\nf(a): " +funcao(equacao, a));
                System.out.println("\nf(b): " +funcao(equacao, b));
                System.out.println("\nxk: " +xk);
                System.out.println("\nf(xk): " +funcao(equacao, xk));
                System.out.println("\n|f(xk|: " + moduloXk);
                System.out.println("\n");
                
                
                if (funcao(equacao, a) * funcao(equacao, b) < 0) {
                    xk = ((a*funcao(equacao, b) - b*funcao(equacao, a)) / (funcao(equacao, b) - funcao(equacao, a)));    
                }
                
                if (funcao(equacao, a) * funcao(equacao, xk) > 0) {
                    a = xk;
                }else {
                    b = xk;
                }
                xk = metodo.ajustarCasas(xk, 4);
                interacao++;
            } while (moduloXk >= erroRelativo);
            System.out.println("A raiz encontrada foi xk = " + xk);
        }
        
    }
    
    public static double funcao(String equacao,double valorX) {
        Function f = new Function("f(x)="+equacao);
        Argument x = new Argument("x="+valorX);
        Expression e2 = new Expression("f(x)",f,x);
        return (double) e2.calculate();
    }
    
        public static double calculaErro(String erro){
        Function f = new Function("f(x)=x");
        Argument x = new Argument("x="+erro);
        Expression e2 = new Expression("f(x)",f,x);
        return (double) e2.calculate();
    }
    
    public double ajustarCasas(double num, int casas) {
        BigDecimal bd = new BigDecimal(num).setScale(casas,RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }
}