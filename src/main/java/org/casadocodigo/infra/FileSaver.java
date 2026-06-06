package org.casadocodigo.infra;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {

    private static final Set<String> ALLOWED_EXTENSIONS = new HashSet<>(
        Arrays.asList("pdf", "epub", "mobi", "zip")
    );

    @Autowired
    private HttpServletRequest request;

    public String write(String baseFolder, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new IllegalArgumentException("Nome de arquivo inválido.");
        }

        String extension = extractExtension(originalFilename);
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new IllegalArgumentException("Tipo de arquivo não permitido: " + extension);
        }

        String safeName = UUID.randomUUID().toString() + "." + extension;

        try {
            String realPath = request.getServletContext().getRealPath("/" + baseFolder);
            File destDir = new File(realPath);
            File dest = new File(destDir, safeName);

            if (!dest.getCanonicalPath().startsWith(destDir.getCanonicalPath() + File.separator)) {
                throw new SecurityException("Tentativa de path traversal detectada.");
            }

            file.transferTo(dest);
            return baseFolder + "/" + safeName;
        } catch (IllegalStateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String extractExtension(String filename) {
        int dot = filename.lastIndexOf('.');
        if (dot < 0) return "";
        return filename.substring(dot + 1).toLowerCase();
    }
}
