package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	VareData vare = new VareData();
    VareData[] vareArr = new VareData[5];
    vare.readFromFile(vareArr);
    vare.UdskrivTilFil(vareArr);
    vare.indFraFil();
    vare.beregnPris(vareArr);
    vare.samletPris(vareArr);
    vare.fakturaPrint(vareArr);

    }
}
