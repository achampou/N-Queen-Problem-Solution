/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e3math;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Javier and Francisco López Cordero
 */
public class E3MATH {

    /**
     * @param args the command line arguments
     */
    
      
    private static List<int[]> combine(int[][] matrix) {
        int sizeArray[] = new int[matrix.length];
        int counterArray[] = new int[matrix.length];
        int total = 1;
        for (int i = 0; i < matrix.length; ++i) {
            sizeArray[i] = matrix[i].length;
            total *= matrix[i].length;
        }
        List<int[]> list = new ArrayList<>(total);
        StringBuilder sb;
        for (int count = total; count > 0; --count) {
            sb = new StringBuilder();
            for (int i = 0; i < matrix.length; ++i) {
                sb.append(matrix[i][counterArray[i]]);
            }
            int tmpi[] = new int[sb.toString().length()];
            for (int tmp = 0; tmp < sb.toString().length(); tmp++) {
                tmpi[tmp] = Integer.parseInt("" + sb.toString().toCharArray()[tmp]);
            }
            list.add(tmpi);
            for (int incIndex = matrix.length - 1; incIndex >= 0; --incIndex) {
                if (counterArray[incIndex] + 1 < sizeArray[incIndex]) {
                    ++counterArray[incIndex];
                    break;
                }
                counterArray[incIndex] = 0;
            }
        }
        return list;
    }
    
    public static List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> results = new ArrayList<List<Integer>>();
    if (nums == null || nums.length == 0) {
        return results;
    }
    List<Integer> result = new ArrayList<>();
    dfs(nums, results, result);
    return results;
}

public static void dfs(int[] nums, List<List<Integer>> results, List<Integer> result) {
    if (nums.length == result.size()) {
        List<Integer> temp = new ArrayList<>(result);
        results.add(temp);
       // System.out.println(Integer.toString(results.size()));
    }        
    for (int i=0; i<nums.length; i++) {
        if (!result.contains(nums[i])) {
            result.add(nums[i]);
            dfs(nums, results, result);
            result.remove(result.size() - 1);
        }
    }
}

public static void i_inicializa_tablero(int[][] tablero, int n)
{   
    for (int i=0; i<n;i++)
        for (int j=0;j<n;j++)
            tablero[i][j] = 0;
}

public static void i_pon_reinas_en_tablero(int [][] tablero, List<Integer> solucion, int n)
{      
    for(int i=0;i<n;i++)
    {
        int columna;
        
        columna = solucion.get(i);
        
        tablero[i][columna] = 1;
    }
   
  
}

public static Boolean i_hay_otra_reina_en_la_derecha(int [][] tablero, int fila_de_la_reina, int columna_de_la_reina, int n)
{
    Boolean hay_otra_reina;
       
    hay_otra_reina = false;
       
    for(int j = columna_de_la_reina + 1;j<n;j++)
        if(tablero[fila_de_la_reina][j]==1)
        {
            hay_otra_reina = true;
            break;
        }
    
    return hay_otra_reina;
}

public static Boolean i_hay_otra_reina_en_la_izquierda(int [][] tablero, int fila_de_la_reina, int columna_de_la_reina, int n)
{
    Boolean hay_otra_reina;
       
    hay_otra_reina = false;
       
    for(int j = columna_de_la_reina - 1;j>0;j--)
        if(tablero[fila_de_la_reina][j]==1)
        {
            hay_otra_reina = true;
            break;
        }
    
    return hay_otra_reina;
}

public static Boolean i_hay_otra_reina_en_la_horizontal(int [][] tablero, int fila_de_la_reina, int columna_de_la_reina, int n)
{
    if (i_hay_otra_reina_en_la_derecha(tablero, fila_de_la_reina, columna_de_la_reina,n)
        ||    i_hay_otra_reina_en_la_izquierda(tablero, fila_de_la_reina, columna_de_la_reina,n))
        {
            return true;
        }
        else
        {
            return false;   
        }        
}

public static Boolean i_hay_otra_reina_encima(int [][] tablero, int fila_de_la_reina, int columna_de_la_reina, int n)
{
    Boolean hay_otra_reina;
       
    hay_otra_reina = false;
       
    for(int i = fila_de_la_reina - 1;i>0;i--)
        if(tablero[i][columna_de_la_reina]==1)
        {
            hay_otra_reina = true;
            break;
        }
    
    return hay_otra_reina;
}

public static Boolean i_hay_otra_reina_debajo(int [][] tablero, int fila_de_la_reina, int columna_de_la_reina, int n)
{
    Boolean hay_otra_reina;
       
    hay_otra_reina = false;
       
    for(int i = fila_de_la_reina + 1;i<n;i++)
        if(tablero[i][columna_de_la_reina]==1)
        {
            hay_otra_reina = true;
            break;
        }
    
    return hay_otra_reina;
}

public static Boolean i_hay_otra_reina_en_la_vertical(int [][] tablero, int fila_de_la_reina, int columna_de_la_reina, int n)
{
    if (i_hay_otra_reina_encima(tablero, fila_de_la_reina, columna_de_la_reina,n)
        ||    i_hay_otra_reina_debajo(tablero, fila_de_la_reina, columna_de_la_reina,n))
    {
        return true;
    }
    else
    {
        return false;   
    }
}

public static Boolean i_hay_otra_reina_diagonal_superior_derecha(int [][] tablero, int fila_de_la_reina, int columna_de_la_reina, int n)
{
    Boolean hay_otra_reina;
    int i;
    int j;
       
    hay_otra_reina = false;
    i = fila_de_la_reina - 1;
    j = columna_de_la_reina + 1;   
    
    while ((i>=0 && j<n) && hay_otra_reina==false)
    {
        if(tablero[i][j]==1)
        {
            hay_otra_reina = true;
            break;
        }
        i--;
        j++;
    }    
    return hay_otra_reina;
}

public static Boolean i_hay_otra_reina_diagonal_superior_izquierda(int [][] tablero, int fila_de_la_reina, int columna_de_la_reina, int n)
{
    Boolean hay_otra_reina;
    int i;
    int j;
       
    hay_otra_reina = false;
    i = fila_de_la_reina - 1;
    j = columna_de_la_reina - 1;   
    
    while ((i>=0 && j >=0) && hay_otra_reina==false)
    {
        if(tablero[i][j]==1)
        {
            hay_otra_reina = true;
            break;
        }
        i--;
        j--;
    }    
    return hay_otra_reina;
}

public static Boolean i_hay_otra_reina_diagonal_inferior_izquierda(int [][] tablero, int fila_de_la_reina, int columna_de_la_reina, int n)
{
   Boolean hay_otra_reina;
    int i;
    int j;
       
    hay_otra_reina = false;
    i = fila_de_la_reina + 1;
    j = columna_de_la_reina - 1;   
    
    while ((i<n && j>=0) && hay_otra_reina==false)
    {
        if(tablero[i][j]==1)
        {
            hay_otra_reina = true;
            break;
        }
        i++;
        j--;
    }    
    return hay_otra_reina;
}

public static Boolean i_hay_otra_reina_diagonal_inferior_derecha(int [][] tablero, int fila_de_la_reina, int columna_de_la_reina, int n)
{
   Boolean hay_otra_reina;
    int i;
    int j;
       
    hay_otra_reina = false;
    i = fila_de_la_reina + 1;
    j = columna_de_la_reina + 1;
    
    while ((i<n && j<n) && hay_otra_reina==false)
    {
        if(tablero[i][j]==1)
        {
            hay_otra_reina = true;
            break;
        }
        i++;
        j++;
    }    
    return hay_otra_reina;
}

public static Boolean i_hay_otra_reina_en_la_diagonal(int [][] tablero, int fila_de_la_reina, int columna_de_la_reina, int n)
{
    if (i_hay_otra_reina_diagonal_superior_derecha(tablero, fila_de_la_reina, columna_de_la_reina,n)
        ||    i_hay_otra_reina_diagonal_superior_izquierda(tablero, fila_de_la_reina, columna_de_la_reina,n)
        ||    i_hay_otra_reina_diagonal_inferior_derecha(tablero, fila_de_la_reina, columna_de_la_reina,n)
        ||    i_hay_otra_reina_diagonal_inferior_izquierda(tablero, fila_de_la_reina, columna_de_la_reina,n))
    {
        return true;
    }
    else
    {
        return false;   
    }
}

public static Boolean i_esta_reina_bien_puesta(int [][] tablero, int fila_de_la_reina, int columna_de_la_reina, int n)
{
    Boolean esta_reina_bien_colocada;
    
    esta_reina_bien_colocada = true;
    
    if (i_hay_otra_reina_en_la_vertical(tablero,fila_de_la_reina,columna_de_la_reina,n) == true
            || i_hay_otra_reina_en_la_horizontal(tablero,fila_de_la_reina,columna_de_la_reina,n) == true
            || i_hay_otra_reina_en_la_diagonal(tablero,fila_de_la_reina,columna_de_la_reina,n) == true)
    {
        esta_reina_bien_colocada = false;
    }   
    
    return esta_reina_bien_colocada;
}
public static Boolean i_tiene_solucion_tablero(int [][] tablero, List<Integer> solucion, int n)
{
    Boolean tiene_solucion;
    
    tiene_solucion = true;
       
    for(int i=0;i<n;i++)
    {
        Boolean reina_posicionada_correctamente; 
        int fila;
        int columna;
        
        fila = i;
        columna = solucion.get(i);
        
        reina_posicionada_correctamente = i_esta_reina_bien_puesta(tablero, fila, columna, n);
                
        if (reina_posicionada_correctamente == false)
        {
            tiene_solucion = false;
            break;
        }
    }
    
    return tiene_solucion;
}

public static Boolean i_cumple_solucion(List<Integer> solucion, int n)
{       
    int[][] tablero = new int [n][n];
    Boolean cumpleSolucion = false;
    
    i_inicializa_tablero(tablero, n);
    i_pon_reinas_en_tablero(tablero, solucion, n);
    
    if(i_tiene_solucion_tablero(tablero,solucion, n))
    {
        cumpleSolucion = true;
    }    
    return cumpleSolucion;
}

public static void i_inicializa_tablero_para_visualizacion(String[][] tablero, int n)
{   
    for (int i=0; i<n;i++)
        for (int j=0;j<n;j++)
            tablero[i][j] = "-";
}

public static void i_pon_reinas_en_tablero_para_visualizacion(String [][] tablero, List<Integer> solucion, int n)
{      
    for(int i=0;i<n;i++)
    {
        int columna;
        
        columna = solucion.get(i);
        
        tablero[i][columna] = "Q";
    }    
}

public static void i_dibuja_tablero(String[][] tablero, int n)
{   

        System.out.print("  ");
        for(int i = 0; i < tablero[0].length; i++){
          System.out.print(i+1 + " ");
        }
        System.out.println();

        for(int i = 0; i < tablero.length; i++){
          System.out.print(i+1 + " ");
          for(int j = 0; j < tablero[i].length; j++){
            System.out.print(tablero[i][j] + " ");
          }
          System.out.println();
        }
}

public static void i_pinta_solucion(List<Integer>solucion, int n)
{
    String[][] tablero;
    
    tablero = new String[n][n];
    
    i_inicializa_tablero_para_visualizacion(tablero, n);
    i_pon_reinas_en_tablero_para_visualizacion(tablero, solucion, n);
    i_dibuja_tablero(tablero, n);
}

public static void i_existe_solucion(List<List<Integer>> results, int n, List<Integer>posiciones_encontradas)
{
    int tamanyo_result;
    
    tamanyo_result = results.size();    
        
    for (int i=0; i<tamanyo_result;i++)
    {
        List<Integer> solucion;
        Boolean cumple;
               
        solucion = results.get(i);        
        cumple = i_cumple_solucion(solucion, n);
        
        if(cumple == true)
        {
            posiciones_encontradas.add(i);
        }
    }
}

     public static int[] addElement(int[] a, int e) {
            a  = Arrays.copyOf(a, a.length + 1);
            a[a.length - 1] = e;
            return a;
    }
     
     public static int [] i_inicializa_valores(int n)
     {
         int [] valores;
         
         valores = new int[n];
         
         for(int i=0; i<n;i++)
             valores[i]=i;
         
         return valores;
     }
/*******************************************************/
    public static void main(String[] args) {
        List<List<Integer>> permutaciones;
        List<Integer> posiciones_encontradas;
        int[] valores;
        int n;
       do{
        Scanner buffer = new Scanner(System.in);
                System.out.println("Introduce un numero para una matriz nxn (n>=0)");
                n=parseInt(buffer.nextLine());
                System.out.println();              
       }while(n<0);  
        valores = i_inicializa_valores(n);
        posiciones_encontradas = new ArrayList<Integer>();
        
        permutaciones = permute(valores);
        
        i_existe_solucion(permutaciones, n, posiciones_encontradas);
        
        if(posiciones_encontradas.size()>0)
        {
            for(int i=0;i<posiciones_encontradas.size();i++)
            {
                int indice_en_permutaciones;
                
                indice_en_permutaciones = posiciones_encontradas.get(i);
                
                System.out.println("Solucion : " + Integer.toString(i+1));
                System.out.println();
                i_pinta_solucion(permutaciones.get(indice_en_permutaciones), n);
                System.out.println();
            }
                
        }            
        else
            System.out.println("No existen soluciones posibles");
        
    }
}
    
    
