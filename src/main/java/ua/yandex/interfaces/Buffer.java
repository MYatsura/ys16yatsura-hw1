package ua.yandex.interfaces;

/**
 *
 * @author Maksym Yatsura
 */
public interface Buffer {
    
    boolean empty();
    boolean full();
    boolean open();
    
    void push(int value);
    void pop();
    int top();
    
}
