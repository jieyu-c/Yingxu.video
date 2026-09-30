package cn.jieyuc.yingxu.video;

/**
 * 单值 Value Object。
 * 相等性由值决定：{@code equals} / {@code hashCode} 必须基于 {@link #value()}。
 * 每个实现类按约定提供 {@code public static SELF of(V raw)}。
 */
public interface VO<V> {
    V value();
}
