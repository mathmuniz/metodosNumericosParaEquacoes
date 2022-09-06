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
    Expression f, f_deriv;
    int i = 1, precisao = 5;
    double xk = 1, f_result = 1, f_deriv_result = 1, x_anterior = 1, anterior_menos_atual = 1, erro = 1;

    MyClass metodo = new MyClass();

    System.out.println("\n\nDigite a função: ");
    expr = input.nextLine();
    System.out.println("\n\nDigite o x inicial: ");
    xk = input.nextDouble();
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
    Argument x = new Argument("x = " + xk);

    while (anterior_menos_atual > erro) {
      f = new Expression(expr, x);
      f_result = f.calculate();
      f_result = metodo.ajustarCasas(f_result, precisao);

      f_deriv = new Expression("der(" + expr + ",x," + xk + ")");
      f_deriv_result = f_deriv.calculate();
      f_deriv_result = metodo.ajustarCasas(f_deriv_result, precisao);

      x_anterior = xk;
      xk = xk - (f_result / f_deriv_result);
      xk = metodo.ajustarCasas(xk, precisao);

      anterior_menos_atual = x_anterior - xk;
      anterior_menos_atual = metodo.ajustarCasas(anterior_menos_atual,
          precisao);

      if (anterior_menos_atual < 0)
        anterior_menos_atual *= -1;

      System.out.printf("---------------------\n\n" + i + "° iteração---------------------");
      System.out.printf("\n\nx atual => %." + precisao + "f", x_anterior);
      System.out.printf("\n\nf(x) => %." + precisao + "f", f_result);
      System.out.printf("\n\nf'(x) => %." + precisao + "f", f_deriv_result);
      System.out.printf("\n\nnovo x => %." + precisao + "f", xk);
      if (i != 1)
        System.out.printf("\n\nAnterior - atual => %." + precisao + "f", anterior_menos_atual);
      i++;
      x.setArgumentValue(xk);
    }
  }
}