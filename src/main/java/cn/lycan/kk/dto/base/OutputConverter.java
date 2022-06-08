package cn.lycan.kk.dto.base;

import org.springframework.lang.NonNull;

import static cn.lycan.kk.utils.BeanUtils.updateProperties;

/**
 * @param <DTO>    the implementation class type
 * @param <DOMAIN> domain type
 * @author Makkapakka
 * @date 2022-6-8
 * @package_name cn.lycan.kk.dto.base
 * @description Converter interface for output DTO.
 *
 * <b>The implementation type must be equal to DTO type</b>
 */
public interface OutputConverter<DTO extends OutputConverter<DTO, DOMAIN>, DOMAIN> {
    
    
    /**
     * Convert from domain.(shallow)
     *
     * @param domain domain data
     * @return converted dto data
     */
    @SuppressWarnings("unchecked")
    @NonNull
    default <T extends DTO> T convertFrom(@NonNull DOMAIN domain) {
        
        updateProperties(domain, this);
        
        return (T) this;
    }
}
