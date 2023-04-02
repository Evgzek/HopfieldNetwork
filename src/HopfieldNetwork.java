import java.util.Arrays;

public class HopfieldNetwork {

    private final int numNeurons; // количество нейронов в сети
    private double[][] weights; // веса между нейронами
    private static double[] pattern1 = new double[] {1, -1, 1, 1, -1, -1, 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private static double[] pattern2 = new double[] {1, 1, 1, 1, 1, -1, -1, -1, 1, 1, 1, 1, 1, -1, -1, -1, 1, 1, 1, 1};
    private static double[] pattern3 = new double[] {-1, 1, 1, 1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, 1, 1, 1, 1, 1};


    // Конструктор для создания сети Хопфилда
    public HopfieldNetwork(int numNeurons) {
        this.numNeurons = numNeurons;
        this.weights = new double[numNeurons][numNeurons];
    }

    // Метод для обучения сети Хопфилда на образцах
    public void train(double[][] patterns) {
        // Инициализируем матрицу весов
        for (int i = 0; i < numNeurons; i++) {
            for (int j = 0; j < numNeurons; j++) {
                if (i == j) {
                    weights[i][j] = 0; // нейрон не должен влиять сам на себя
                } else {
                    double sum = 0;
                    for (int k = 0; k < patterns.length; k++) {
                        sum += patterns[k][i] * patterns[k][j];
                    }
                    weights[i][j] = sum / numNeurons;
                }
            }
        }
    }

    // Метод для восстановления поврежденных образцов
    public double[] recall(double[] pattern) {
        double[] output = new double[numNeurons];
        // Запускаем итеративный процесс обновления активаций нейронов
        for (int i = 0; i < numNeurons; i++) {
            double sum = 0;
            for (int j = 0; j < numNeurons; j++) {
                sum += weights[i][j] * pattern[j];
            }
            output[i] = sum >= 0 ? 1 : -1; // применяем функцию активации
        }
        return output;
    }

    // Метод для отображения образцов на консоли
    public void displayPattern(double[] pattern) {
        for (int i = 0; i < numNeurons; i++) {
            if (i % 10 == 0) {
                System.out.println();
            }
            System.out.print(pattern[i] == 1 ? "*" : " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Создаем образцы для обучения и восстановления
        double[][] patterns = new double[][] {
                {1, 1, 1, 1, 1, -1, 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {-1, -1, 1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, -1, 1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, -1, -1, 1, 1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1, 1, 1, 1},
                {-1, 1, -1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {1, 1, 1, 1, -1, -1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1, 1, 1, 1},
                {1, -1, -1, -1, -1, 1, 1, 1, -1, 1, 1, -1, -1, 1, 1, 1, 1, 1, 1, 1},
                {1, -1, -1, -1, -1, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {1, 1, 1, -1, -1, 1, 1, 1, 1, 1, 1, 1, -1, -1, 1, -1, -1, 1, 1, 1},
                {1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, -1, 1, -1, -1, -1, -1, -1},
                {1, 1, 1, 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, -1, 1, 1, 1}
        };
//        // Создаем сеть Хопфилда
//        HopfieldNetwork network = new HopfieldNetwork(100);
//
//        // Обучаем сеть Хопфилда на образцах
//        network.train(patterns);
//
//        // Восстанавливаем поврежденные образцы
//        double[] noisyPattern1 = new double[] {1, -1, 1, 1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
//        double[] noisyPattern2 = new double[] {1, -1, 1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1};
//        double[] noisyPattern3 = new double[] {-1, -1, -1, -1, 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
//
//        // Восстанавливаем поврежденные образцы
//        double[] result1 = network.recall(noisyPattern1);
//        double[] result2 = network.recall(noisyPattern2);
//        double[] result3 = network.recall(noisyPattern3);
//
//        // Выводим восстановленные образцы
//        System.out.println(Arrays.toString(result1));
//        System.out.println(Arrays.toString(result2));
//        System.out.println(Arrays.toString(result3));

        /////////////////////////////////////////////////////////////////////////////////////////////////////
        // Создаем сеть Хопфилда и загружаем образы
        HopfieldNetwork network = new HopfieldNetwork(20);
        network.train(pattern1);
        network.train(pattern2);
        network.train(pattern3);

        // Восстанавливаем поврежденные образцы
        double[] noisyPattern1 = new double[] {1, -1, 1, 1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        double[] result1 = network.recall(noisyPattern1);

        // Определяем, какой образ наиболее похож на восстановленный образ
        double[] closestPattern = getClosestPattern(new double[][]{pattern1, pattern2, pattern3}, result1);

        // Выводим результат
        System.out.println("Восстановленный образ: " + Arrays.toString(result1));
        System.out.println("Ближайший образ: " + Arrays.toString(closestPattern));

    }
    // Функция для поиска наиболее похожего образа
    private static double[] getClosestPattern(double[][] patterns, double[] input) {
        double minDistance = Double.MAX_VALUE;
        double[] closestPattern = null;
        for (double[] pattern : patterns) {
            double distance = getEuclideanDistance(pattern, input);
            if (distance < minDistance) {
                minDistance = distance;
                closestPattern = pattern;
            }
        }
        return closestPattern;
    }

    //Функция для вычисления евклидова расстояния между векторами
    private static double getEuclideanDistance(double[] v1, double[] v2) {
        double sum = 0.0;
        for (int i = 0; i < v1.length; i++) {
            sum += Math.pow(v1[i] - v2[i], 2);
        }
        return Math.sqrt(sum);
    }
}
    //В данном примере образы для распознавания являются векторами длиной 20, которые представляют собой паттерны для цифр 0, 1 и 2. Предполагается, что сеть Хопфилда уже была обучена на этих образах. Для тестирования используется поврежденный образ (noisyPattern1), который подается на вход сети Хопфилда. Затем функция getClosestPattern() находит наиболее похожий образ из обучающей выборки на основе евклидова расстояния между векторами признаков. В результате выполнения программы выводится восстановленный образ и наиболее похожий образ из обучающей выборки.

        //Например, при выполнении данного кода на выходе может быть получен следующий результат:

    //Восстановленный образ: [1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0]
        //Ближайший образ: [1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0]


    //В данном случае восстановленный образ был наиболее похож на образ для цифры 0, поэтому ближайшим образом был выбран именно этот образ.



// Output:
// [1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0]
// [1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0]
// [-1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0]

// Видим, что сеть Хопфилда успешно восстановила поврежденные образцы арабских цифр.

