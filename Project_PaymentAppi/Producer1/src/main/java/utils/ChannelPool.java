package utils;

public interface ChannelPool<T> {
    T getChannel();
    void releaseChannel(T channel);
    int getSize();
}
