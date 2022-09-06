import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.mariuszgromada.math.mxparser.*;

public class MyClass {

  public double ajustarCasas(double num, int casas) {
    BigDecimal bd = new BigDecimal(num).setScale(casas, RoundingMode.HALF_EVEN);
    return bd.doubleValue();
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String expr;
    Expression fa, fb;
    int i = 1, precisao = 5;
    double a = 1, b = 1, xk = 1, fa_result = 1, fb_result = 1, x_atual = 1, x_anterior = 1, atual_menos_anterior = 2, erro = 1;

    MyClass metodo = new MyClass();

    System.out.println("\n\nDigite a função: ");
    expr = input.nextLine();
    System.out.println("\n\nDigite o ponto inicial do intervalo: ");
    a = input.nextDouble();
    System.out.println("\n\nDigite o ponto final do intervalo: ");
    b = input.nextDouble();
    System.out.println("\n\nDigite o erro: ");
    erro = input.nextDouble();

    double erro_informado = erro;
    int precisao_sugerida = 0;
    while (erro_informado < 1) {
      erro_informado *= 10;
      precisao_sugerida++;
    }
    precisao_sugerida++;

    System.out.println("\n\nNúmero de precisão em casas decimais (sugerimos " +
        precisao_sugerida + " para esse erro):");
    precisao = input.nextInt();
    
    Argument x = new Argument("x = " + a);
    
     fa = new Expression(expr, x);
     xk = a;
     fa_result = fa.calculate();
     fa_result = metodo.ajustarCasas(fa_result, precisao);
     System.out.println("\n\n" + i + " iteração:");
     System.out.println("\n\nxk: " + xk);
     System.out.println("\n\nf(x): " + fa_result);
     i++;
    
     x.setArgumentValue(b);
     fb = new Expression(expr, x);
     xk = b;
     fb_result = fb.calculate();
     fb_result = metodo.ajustarCasas(fb_result, precisao);
     System.out.println("\n\n" + i + " iteração:");
     System.out.println("\n\nxk: " + xk);
     System.out.println("\n\nf(x): " + fb_result);
     i++;
    
    
    while(atual_menos_anterior > erro) {
     xk = (a * fb_result - b * fa_result) / (fb_result - fa_result);  
     xk = metodo.ajustarCasas(xk, precisao);
     a = b;
     fa_result = fb_result;
     b = xk;
     x.setArgumentValue(b);
     fb = new Expression(expr, x);
     fb_result = fb.calculate();
     fb_result = metodo.ajustarCasas(fb_result, precisao);
     System.out.println("\n\n" + i + " iteração:");
     System.out.println("\n\nnovo xk: " + xk);
     atual_menos_anterior = xk - a;
     if(atual_menos_anterior < 0) atual_menos_anterior *= -1;
     System.out.println("\n\nAtual - anterior: " + atual_menos_anterior);
     i++;
    }
  }
}