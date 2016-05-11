package pl.com.softproject.currency.assembler;

import java.util.List;

/**
 * Interface for assemblers two similar class
 *
 * @param <T1> dbo objects type
 * @param <T2> dto objects type
 *
 * @author Leszek Bednorz
 */
public interface Assembler<T1, T2> {

    /**
     * Converet dto objects to dbo objects
     *
     * @return dbo object
     */
    T1 assemblyToDbo(T2 dto);

    /**
     * Converet dbo objects to dto objects
     *
     * @return dto object
     */
    T2 assemblyToDto(T1 dbo);

    List<T2> assemblyToDto(List<T1> dbo);

    List<T1> assemblyToDbo(List<T2> dto);

}
