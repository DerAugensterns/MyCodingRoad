package cn.adiong.designpattern;

/**
 * @Author: 阿威
 * @Date: 2020/9/25 10:27
 * @Description：
 */
public class CommandPattern {
    public static void main(String[] args) {
        Invoker invoker = new Invoker(new ConcreteCommand(new Receiver()));
        invoker.call();
    }
}

/**
 * 真正的命令执行者
 */
class Receiver {
    public void action() {
        System.out.println("战斗!");
    }
}

interface Command {
    void execute();
}

class ConcreteCommand implements Command {

    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //命令调用前后可执行相关处理
        receiver.action();
    }
}

class Invoker {
    /**
     * 通过容器List<Command>容纳多条命令，进行批处理，事务的底层原理
     */
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void call() {
        command.execute();
    }
}
