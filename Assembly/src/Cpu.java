public class Cpu implements ICpu {
    private final Mem_cpu memory;

    public Cpu() {
        // Инициализация памяти: 1024 ячейки, 4 регистра (A, B, C, D)
        this.memory = new Mem_cpu(1024, 4);
    }

    @Override
    public void exec(Command c) {
        String name = c.getName();
        String[] args = c.getArgs();

        switch (name) {
            case "init":
                // memory[address] = value
                memory.setMem(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
                break;

            case "ld":
                // register[reg] = memory[address]
                int regIndexLd = getRegisterIndex(args[0]);
                int memValue = memory.getMem(Integer.parseInt(args[1]));
                memory.setRegister(regIndexLd, memValue);
                break;

            case "mv":
                // Переместить значение между регистрами
                int destIndex = getRegisterIndex(args[1]);
                int srcIndex = getRegisterIndex(args[0]);
                memory.setRegister(srcIndex, memory.getRegister(destIndex));
                break;

            case "add":
                // D = A + B
                int sum = memory.getRegister(0) + memory.getRegister(1);
                memory.setRegister(3, sum); // D
                break;

            case "div":
                // Деление D = A / C
                int dividend = memory.getRegister(0); // A
                int divisor = memory.getRegister(2); // C
                if (divisor != 0) {
                    memory.setRegister(3, dividend / divisor); // D
                } else {
                    System.out.println("Ошибка: Деление на ноль");
                }
                break;

            case "print":
                printRegisters();
                break;

            default:
                System.out.println("Неизвестная команда: " + name);
        }
    }

    private void printRegisters() {
        System.out.println("Регистры:");
        System.out.println("A: " + memory.getRegister(0));
        System.out.println("B: " + memory.getRegister(1));
        System.out.println("C: " + memory.getRegister(2));
        System.out.println("D: " + memory.getRegister(3));
    }

    private int getRegisterIndex(String regName) {
        switch (regName.toLowerCase()) {
            case "a": return 0;
            case "b": return 1;
            case "c": return 2;
            case "d": return 3;
            default:
                throw new IllegalArgumentException("Неизвестный регистр: " + regName);
        }
    }
}
