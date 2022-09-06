import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.mariuszgromada.math.mxparser.*;

public class MyClass {

  public double ajustarCasas(double num, int casas) {
    BigDecimal bd = new BigDecimal(num).setScale(casas, RoundingMode.HALF_EVEN);
    return bd.doubleValue();
  }

  private double funcao(String expr, double num) {
    Argument x = new Argument("x = " + num);
    Expression calc = new Expression(expr, x);
    return calc.calculate();
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    double a = 0, b = 0;
    int i = 0;
    boolean rangeFound = false;
    String expr;

    MyClass metodo = new MyClass();
    
    System.out.println("\n\nDigite a função: ");
    expr = input.nextLine();

    //testando valores positivos
    a = 0;
    b = 1;
    if(!rangeFound) {
      for(i = 0; i < 10000; i++) {
        if(metodo.funcao(expr, a) * metodo.funcao(expr, b) < 0) {
          System.out.println("\n\nTem uma raiz real entre " + a + " e " + b);
          rangeFound = true;
          break;
        }
        a = a + 0.1;
        a = metodo.ajustarCasas(a, 1);
        b = b + 0.1;
        b = metodo.ajustarCasas(b, 1);
      }
    }

    //testando valores negativos
    a = 0;
    b = -1;
    if(!rangeFound) {
      for(i = 0; i > -10000; i--) {
        if(metodo.funcao(expr, a) * metodo.funcao(expr, b) < 0) {
          System.out.println("\n\nTem uma raiz real entre " + a + " e " + b);
          rangeFound = true;
          break;
        }
        a = a - 0.1;
        a = metodo.ajustarCasas(a, 1);
        b = b - 0.1;
        b = metodo.ajustarCasas(b, 1);
      }
    }
  }
}