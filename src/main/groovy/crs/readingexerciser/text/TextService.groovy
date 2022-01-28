package crs.readingexerciser.text

class TextService {
    Random random = new Random()
    List<String> words = new ArrayList<>()


    TextService(String textFile) {
        new File(textFile).readLines().join()
                .split()
                .toUnique()
                .each {
                    words << it
                }
    }

    String randomWord() {
        int index = random.nextInt(words.size())
        return words[index].toUpperCase()
    }
}
