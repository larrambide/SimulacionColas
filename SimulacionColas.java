/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacioncolas;

/**
 *
 * @author 
 */
public class SimulacionColas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here}
        double TLLmedio=74.314;
        double TSmedio1;
        double TSmedio2;
        double Tlimite =100;
        double Reloj=0;
        double TET1=0;
        double TET2=0;
        double TEM1=0;
        double TEM2=0;
        double TO1=0;
        double TO2=0;
        double TS1=33;
        double TS2=25;
        double TLL=30;
        double delta=0;
        int cola1=0;
        int cola2=0;
        int NCA1=0;
        int NCA2=0;
        int NCLL=0;
        
        
        while(Reloj<Tlimite){
        if (TLL==0 && TS1==0 && TS2==0){
        delta = TLLmedio;
        }
        
        else{
        
            if (TLL<=TS1){
                if (TLL<=TS2){
                delta = TLL;
                }
                else{
                delta = TS2;
                }
            }
        
            else{
                if(TS1<=TS2){
                   delta = TS1;
                }
                else{
                    if(TS2<=TLL){
                    delta = TS2;
                    } 
                }           
            }
            Reloj = Reloj + delta; //Actualización del tiempo en el sistema
            TET1 = TET1 +  cola1 * delta; //Cálculo de tiempo de espera total
            TET2 = TET2 + cola2 * delta; //Cálculo de tiempo de espera total
            TLL = TLL - delta; //Ajustar tiempo entre llegadas
            //System.out.println("\nRejoj: "+Reloj+"\ndelta "+delta+"\nCola 1: "+cola1+"\nCola 2: "+cola2+"\nTS1: "+TS1+"\nTS2: "+TS2+"\nNCLL: "+NCLL+"\nTO1: "+TO1+"\nTO2: "+TO2);
            
             //Checar si llego
            if (TLL==0){ 
               NCLL++; //Incrementar cantidad de clientes
               //Seleccionar la cola con menor tamaño
               if (cola1<cola2){
                 cola1++; //Incrementar tamaño de cola
               }
               else{
                 cola2++; //Incrementar tamaño de cola  
               }
            }
            TLL = varEXP(); //Generar próxima llegada
            
            TS1 = TS1+delta; //Ajustar tiempo de servicio 
            
            //
            if(TS1<0){
                TO1 = TO1 - TS1;
                TS1=0;
            }
            else if (TS1==0){
                    NCA1++;
                 }
            if (cola1>0){
            cola1--;
            TS1 = varEXP();
            }
            
            TS2 = TS2+delta; //Ajustar tiempo de servicio 
            
            //
            if(TS2<0){
                TO2 = TO2 - TS2;
                TS2=0;
            }
            else if (TS2==0){
                    NCA2++;
                 }
            if (cola2>0){
            cola2--;
            TS2 = varEXP();
            }
            
            System.out.println("\nRejoj: "+Reloj+"\ndelta "+delta+"\nCola 1: "+cola1+"\nCola 2: "+cola2+"\nTS1: "+TS1+"\nTS2: "+TS2+"\nNCLL: "+NCLL+"\nTO1: "+TO1+"\nTO2: "+TO2);
            
            }//end else delta          
        }//end while
        TEM1 = TET1/Tlimite;
        TEM2 = TET2/Tlimite;
        System.out.println("\nResultados Finales: \nTiempo de Ocio 1: "+TO1+"\nTiempo de Ocio 2: "+TO2+"\nTiempo Promedio de Espera 1: "+TEM1+"\nTiempo Promedio de Espera 2: "+TEM2);
        
}//end main  

//Generador de variables con distribución exponencial
    public static double  varEXP (){
    
     int A=29;//multiplicador
        int C=47;//constante aditiva
        double X=71;//semilla
        int M=191;//módulo 
        double R;//numero aleatorio resultante
        double Lambda=0.5;
        // TODO code application logic here
        
            R = (A*X+C)%M;
            X=R;
            R=R/M;
            R= -(Math.log((R/Lambda)/Math.log(10)));
            return (R);
    }//end varEXP   
}//end class

