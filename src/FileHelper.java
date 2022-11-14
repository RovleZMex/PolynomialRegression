import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHelper {
    final File file;
    Scanner scanner;
    int numberOfPoints = 0;

    public FileHelper(final File file) {
        this.file = file;
        resetScanner();
    }

    public void resetScanner(){
        try {
            scanner = new Scanner(file);
        } catch (Exception e) {
            throw new RuntimeException("File not found");
        }
    }
    public int getSize(){
        return numberOfPoints;
    }
    public double[] getXData() {
        resetScanner();
        List<Double> data = new ArrayList<>();
        String tempString;
        tempString = scanner.nextLine();
        Scanner inLineScanner = new Scanner(tempString);
        while (inLineScanner.hasNextDouble()) {
            data.add(inLineScanner.nextDouble());
        }
        double[] result = new double[data.size()];
        if(numberOfPoints == 0){
            numberOfPoints = data.size();
        }
        for(int i = 0; i < data.size(); i++){
            result[i] = data.get(i);
        }
        return  result;
    }

    public double[] getYData() {
        resetScanner();
        List<Double> data = new ArrayList<>();
        String tempString;
        scanner.nextLine();
        tempString = scanner.nextLine();
        Scanner inLineScanner = new Scanner(tempString);
        while (inLineScanner.hasNextDouble()) {
            data.add(inLineScanner.nextDouble());
        }
        double[] result = new double[data.size()];
        if(numberOfPoints == 0){
            numberOfPoints = data.size();
        }
        for(int i = 0; i < data.size(); i++){
            result[i] = data.get(i);
        }
        return  result;
    }
}
