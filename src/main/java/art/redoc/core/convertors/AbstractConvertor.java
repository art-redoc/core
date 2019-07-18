package art.redoc.core.convertors;

import art.redoc.core.dto.ListResultDTO;
import art.redoc.core.dto.PageResultDTO;
import art.redoc.core.dto.ResultDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract convertor, the default params {@code forListView} is false.
 *
 */
public abstract class AbstractConvertor<Model, DTO> {

    public abstract Model toModel(final DTO dto);

    public DTO toDTO(final Model model) {
        return this.toDTO(model, false);
    }

    public abstract DTO toDTO(final Model model, boolean forListView);

    public final List<Model> toListModel(final List<DTO> dtoList) {
        final List<Model> modelList = dtoList.stream().map(dto -> this.toModel(dto)).collect(Collectors.toList());
        return modelList;
    }

    public final List<DTO> toListDTO(final List<Model> modelList) {
        return toListDTO(modelList, false);
    }

    public final List<DTO> toListDTO(List<Model> modelList, boolean forListView) {
        final List<DTO> dtoList = modelList.stream().map(model -> this.toDTO(model, forListView)).collect(Collectors.toList());
        return dtoList;
    }

    public final Page<DTO> toPageDTO(final Page<Model> modelPage) {
        return toPageDTO(modelPage, false);
    }

    public final Page<DTO> toPageDTO(final Page<Model> modelPage, boolean forListView) {
        final List<Model> modelList = modelPage.getContent();
        final List<DTO> dtoList = this.toListDTO(modelList, forListView);
        final long totalElements = modelPage.getTotalElements();
        final Page<DTO> dtoPage = new PageImpl<>(dtoList, this.getPageable(modelPage), totalElements);
        return dtoPage;
    }

    public final ResultDTO<DTO> toResultDTO(final Model model) {
        return toResultDTO(model, false);
    }

    public final ResultDTO<DTO> toResultDTO(final Model model, boolean forListView) {
        final DTO dto = (model == null) ? null : this.toDTO(model, forListView);
        final ResultDTO<DTO> resultDTO = ResultDTO.success(dto);
        return resultDTO;
    }

    public final ListResultDTO<DTO> toResultDTO(final List<Model> modelList) {
        return toResultDTO(modelList, false);
    }

    public final ListResultDTO<DTO> toResultDTO(final List<Model> modelList, boolean forListView) {
        final List<DTO> dtoList = this.toListDTO(modelList, forListView);
        final ListResultDTO<DTO> resultDTO = ListResultDTO.success(dtoList);
        return resultDTO;
    }

    public final PageResultDTO<DTO> toResultDTO(final Page<Model> modelPage) {
        return toResultDTO(modelPage, false);
    }

    public final PageResultDTO<DTO> toResultDTO(final Page<Model> modelPage, boolean forListView) {
        final List<DTO> dtoList = this.toListDTO(modelPage.getContent(), forListView);
        final PageResultDTO<DTO> resultDTO = PageResultDTO.success(dtoList, modelPage);
        return resultDTO;
    }

    public Pageable getPageable(final Page<Model> modelPage) {
        try {
            final Field pageableField = PageImpl.class.getSuperclass().getDeclaredField("pageable");
            pageableField.setAccessible(true);
            return (Pageable) pageableField.get(modelPage);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
        }
        return null;
    }
}
