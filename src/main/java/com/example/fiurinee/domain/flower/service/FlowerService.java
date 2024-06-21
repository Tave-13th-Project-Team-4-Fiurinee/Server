package com.example.fiurinee.domain.flower.service;

import com.example.fiurinee.domain.flower.dto.FlowerResponseDTO;
import com.example.fiurinee.domain.flower.entity.Flower;
import com.example.fiurinee.domain.flower.repository.FlowerRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class FlowerService {
    @Autowired
    private FlowerRepository flowerRepository;

    public void parseAndSaveCsv(String filePath) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), Charset.forName("EUC-KR")))) {
            List<String[]> records = reader.readAll();
            System.out.println("CSV 파일 읽기 성공: " + records.size() + "개의 레코드 발견");
            for (String[] record : records) {
                System.out.println("레코드: " + String.join(", ", record)); // 디버깅 로그 추가
                if (record.length < 8) { // 필드 개수 확인
                    System.err.println("컬럼이 부족한 레코드 건너뜀: " + String.join(", ", record));
                    continue; // 컬럼이 부족한 경우 건너뜁니다.
                }

                Long period = computePeriod(record[2], record[3]);
                if (period == null) {
                    System.err.println("유효하지 않은 period 값, 레코드 건너뜀: " + String.join(", ", record));
                    continue;
                }

                URL imageUrl = toURL(record[7]);
                if (imageUrl == null) {
                    System.err.println("유효하지 않은 URL 값, 레코드 건너뜁: " + String.join(", ", record));
                    continue;
                }

                Flower flower = Flower.builder()
                        .name(record[1])
                        .period(period)
                        .flowerLanguage(record[5]) // 꽃말 위치 변경
                        .explain(record[6]) // 설명 위치 변경
                        .image(imageUrl)
                        .build();

                flowerRepository.save(flower);
                System.out.println("저장 성공: " + flower.getName()); // 디버깅 로그 추가
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private Long computePeriod(String month, String day) {
        try {
            if (month != null && !month.isEmpty()) {
                System.out.println("month: " + month); // 디버깅 로그 추가
                Long period = Long.parseLong(month) * 100;
                if (day != null && !day.isEmpty()) {
                    System.out.println("day: " + day); // 디버깅 로그 추가
                    period += Long.parseLong(day);
                }
                return period;
            }
        } catch (NumberFormatException e) {
            System.err.println("숫자 형식 오류: " + e.getMessage());
        }
        return null;
    }

    private URL toURL(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            System.err.println("유효하지 않은 URL: " + urlString);
            return null;
        }
    }

    public List<FlowerResponseDTO> getSeasonFlowers() {
        int currentMonth = LocalDate.now().getMonthValue();
        Long startPeriod = (long) (currentMonth * 100);
        Long endPeriod = startPeriod + 99; // 현재 월의 마지막 날을 포함하도록 설정
        List<Flower> flowers = flowerRepository.findByPeriodMonth(startPeriod, endPeriod);

        if (flowers.isEmpty()) {
            System.out.println("No flowers found for period month: " + currentMonth);
            return List.of();
        }

        Random random = new Random();
        List<Flower> randomFlowers = flowers.stream()
                .skip(random.nextInt(flowers.size() - 5 + 1)) // random skip
                .limit(5)
                .collect(Collectors.toList());

        return randomFlowers.stream()
                .map(FlowerResponseDTO::of)
                .collect(Collectors.toList());
    }

    public FlowerResponseDTO getTodayFlower() {
        LocalDate today = LocalDate.now();
        Long todayPeriod = (long) (today.getMonthValue() * 100 + today.getDayOfMonth());
        List<Flower> flowers = flowerRepository.findByPeriodMonth(todayPeriod, todayPeriod);

        if (flowers.isEmpty()) {
            System.out.println("No flowers found for period: " + todayPeriod);
            return null;
        }

        Random random = new Random();
        Flower randomFlower = flowers.get(random.nextInt(flowers.size()));

        return FlowerResponseDTO.of(randomFlower);
    }
}
