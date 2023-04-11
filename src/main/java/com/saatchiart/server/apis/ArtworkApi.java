package com.saatchiart.server.apis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.saatchiart.server.Ultils.UploadImage;
import com.saatchiart.server.dto.ArtworkCatDTO;
import com.saatchiart.server.dto.ArtworkDTO;
import com.saatchiart.server.dto.ListDTO;
import com.saatchiart.server.service.impl.ArtworkService;


@RestController
@RequestMapping(value = "/api/artwork")
public class ArtworkApi {

    @Autowired
    private ArtworkService artworkService;

    @GetMapping
    public ApiResponse<ListDTO<ArtworkCatDTO>> getArtwork(@RequestParam(value="page",required = false) Integer page,
                                    @RequestParam(value="limit",required = false) Integer limit) throws IOException{
        ListDTO<ArtworkCatDTO> dataApi = new ListDTO<ArtworkCatDTO>();
        
        if (page != null && limit != null) {
			dataApi.setPage(page);
			Pageable pageable = PageRequest.of(page - 1, limit);
			dataApi.setListResult(artworkService.findAll(pageable));
			dataApi.setTotalPage((int) Math.ceil((double) (artworkService.totalItem()) / limit));
		} else {
			dataApi.setListResult(artworkService.findAll());
		}
        ApiResponse<ListDTO<ArtworkCatDTO>> result = new ApiResponse<>("Success", HttpStatus.OK, dataApi);
		return result;
    }

    @PostMapping
    public ArtworkDTO createArtwork(@ModelAttribute ArtworkDTO model, @RequestParam("thumbnail") MultipartFile thumbnail,@RequestParam("album") MultipartFile[] album) throws Exception {
        UploadImage thumbnailUpload = new UploadImage(thumbnail);
        model.setThumbnailUrl(thumbnailUpload.getUrl());
        List<String> urlAlbum = new ArrayList<>();
        for (MultipartFile item : album) {
            UploadImage itemFile = new UploadImage(item);
            urlAlbum.add(itemFile.getUrl());
        }
        model.setAlbumUrls(urlAlbum);
        return artworkService.save(model);
    }
}
