package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class VareData implements Serializable {
    private int antal;
    private String vareNavn;
    private float stkPris;


    public void indFraFil (){
        try{
            int i = 0;
            VareData[] varrArr1 = new VareData[5];
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("varer.ser"));
            for (int j = 0; j < 5; j++) {
                varrArr1[i]=(VareData) ois.readObject();
                i++;
            }
            printOrders(varrArr1);
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }


    public void fakturaPrint(VareData[] varrArr) throws IOException {
        File faktura = new File("faktura.txt");
        BufferedWriter bufWrite = new BufferedWriter(new FileWriter("faktura.txt", false));
        float[] prisArr = new float[5];
        for (int i = 0; i < 5; i++) {
            if (varrArr[i].getAntal()>10){
                prisArr[i]=(varrArr[i].getStkPris()/100*85);
            } else if (varrArr[i].getAntal()<10){
                prisArr[i]=(varrArr[i].getStkPris());
            }
        }
        System.out.println("FAKTURA");
        bufWrite.write("FAKTURA"+"\n");
        for (int i = 0; i < 5; i++) {
            double x = varrArr[i].getStkPris() * varrArr[i].getAntal();
            float rabaty = prisArr[i];
            System.out.println(varrArr[i].getVareNavn() + " antal: " + varrArr[i].getAntal() + " pris: " + x);
            bufWrite.write(varrArr[i].getVareNavn() + " antal: " + varrArr[i].getAntal() + " pris: " + x +"\n");
            System.out.printf("rabat: -%2.2f \n", rabaty);
            bufWrite.write("rabat: -"+ rabaty+"\n");
        }
        float k = 0;
        for (int i = 0; i < 5; i++) {
            k+=prisArr[i]*varrArr[i].getAntal();
        }
        System.out.printf("Samlet pris: %2.2f", k);
        bufWrite.write("Samlet pris: "+k+"\n");
        bufWrite.close();
    }

    public void samletPris (VareData[] varrArr){
        float[] prisArr = new float[5];
        for (int i = 0; i < 5; i++) {
            if (varrArr[i].getAntal()>10){
                prisArr[i]=(varrArr[i].getStkPris()/100*85);
            } else if (varrArr[i].getAntal()<10){
                prisArr[i]=(varrArr[i].getStkPris());
            }
        }
        double x = 0;
        double y = 0;
        for (int i = 0; i < 5; i++) {
            x += prisArr[i]*varrArr[i].getAntal();
            y += varrArr[i].getStkPris()*varrArr[i].getAntal();
        }
        System.out.printf("Samlet pris for indkøb med rabat: %2.2f kr. \n", x);
        System.out.println("Samlet pris for indkøb uden rabat: "+y+"kr.");
    }


    public void beregnPris (VareData[] varrArr){
        float[] prisArr = new float[5];
        for (int i = 0; i < 5; i++) {
           if (varrArr[i].getAntal()>10){
               prisArr[i]=(varrArr[i].getStkPris()/100*85);
           } else if (varrArr[i].getAntal()<10){
               prisArr[i]=(varrArr[i].getStkPris());
           }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(varrArr[i].getVareNavn()+"pris uden rabat: "+varrArr[i].getStkPris());
            System.out.println(varrArr[i].getVareNavn()+"pris med rabat: "+prisArr[i]);
        }

    }

    public void UdskrivTilFil(VareData[] vareArr){
        try {
            File minSerFil = new File("varer.ser");
            FileOutputStream fos = new FileOutputStream(minSerFil);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (int i = 0; i < 5; i++) {
                oos.writeObject(vareArr[i]);
            }
            System.out.println("Object created and file closed.");
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printOrders(VareData[] vareArr){
        for (VareData data: vareArr) {
            System.out.println(data.antal+" " + data.vareNavn + " " + data.stkPris);
        }
    }
    public void readFromFile(VareData[] vareArr){
        try {
            File minFil = new File("bestilling.txt");
            Scanner myReader = new Scanner(minFil);
            for (int i = 0; i < 5; i++) {
                VareData order = new VareData(myReader.nextInt(), myReader.next(), myReader.nextFloat());
                vareArr[i] = order;
            }
            myReader.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "VareData{" +
                "antal=" + antal +
                ", vareNavn='" + vareNavn + '\'' +
                ", stkPris=" + stkPris +
                '}';
    }

    public void udskrivArrayVare(VareData[] vareArr){
        for (int i = 0; i < 5; i++) {
            System.out.println(vareArr[i].getAntal() + " " + vareArr[i].getVareNavn() + " " + vareArr[i].getStkPris());
        }

    }
    public VareData(){}

    public VareData(int antal, String vareNavn, float stkPris) {
        this.antal = antal;
        this.vareNavn = vareNavn;
        this.stkPris = stkPris;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public String getVareNavn() {
        return vareNavn;
    }

    public void setVareNavn(String vareNavn) {
        this.vareNavn = vareNavn;
    }

    public float getStkPris() {
        return stkPris;
    }

    public void setStkPris(float stkPris) {
        this.stkPris = stkPris;
    }
}
