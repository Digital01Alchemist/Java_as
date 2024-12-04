public class Mem_cpu {
    private int[] memory;
    private int[] register;

    public Mem_cpu(int mem_cnt, int reg_cnt) {
        memory = new int[mem_cnt];
        register = new int[reg_cnt];
    }

    public int[] getMem() {
        return memory;
    }

    public int getMem(int index) {
        return memory[index];
    }

    public void setMem(int index, int value) {
        memory[index] = value;
    }

    public int[] getRegister() {
        return register;
    }

    public int getRegister(int index) {
        return register[index];
    }

    public void setRegister(int index, int value) {
        register[index] = value;
    }
}
