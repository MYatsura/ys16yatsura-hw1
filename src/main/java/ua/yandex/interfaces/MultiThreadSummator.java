package ua.yandex.interfaces;

/**
 *
 * @author Maksym Yatsura
 */
public interface MultiThreadSummator<T> {
    
    T multiThreadCalculation(double param, int numThreads);
}
