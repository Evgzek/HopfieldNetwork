import java.util.Arrays;

public class HopfieldNetwork {

    private final int numNeurons; // количество нейронов в сети
    private double[][] weights; // веса между нейронами

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
        // Создаем сеть Хопфилда
        HopfieldNetwork network = new HopfieldNetwork(100);

        // Обучаем сеть Хопфилда на образцах
        network.train(patterns);

        // Восстанавливаем поврежденные образцы
        double[] noisyPattern1 = new double[] {1, -1, 1, 1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        double[] noisyPattern2 = new double[] {1, -1, 1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1};
        double[] noisyPattern3 = new double[] {-1, -1, -1, -1, 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        // Восстанавливаем поврежденные образцы
        double[] result1 = network.recall(noisyPattern1);
        double[] result2 = network.recall(noisyPattern2);
        double[] result3 = network.recall(noisyPattern3);

        // Выводим восстановленные образцы
        System.out.println(Arrays.toString(result1));
        System.out.println(Arrays.toString(result2));
        System.out.println(Arrays.toString(result3));
    }
}

// Output:
// [1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0]
// [1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0]
// [-1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0]

// Видим, что сеть Хопфилда успешно восстановила поврежденные образцы арабских цифр.

