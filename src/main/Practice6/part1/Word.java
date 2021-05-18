package main.Practice6.part1;

public class Word implements Comparable<Word> {
    private String content;
    private int frequency;

    public Word(String content, int frequency) {
        this.content = content;
        this.frequency = frequency;
    }

    public String getContent() {
        return content;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public int compareTo(Word o) {
        int result = o.frequency - frequency;
        if(result == 0) result = content.compareTo(o.content);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        if (frequency != word.frequency) return false;
        return content != null ? content.equals(word.content) : word.content == null;
    }

    @Override
    public int hashCode() {
        int result = content != null ? content.hashCode() : 0;
        result = 31 * result + frequency;
        return result;
    }

    @Override
    public String toString() {
        return "Word{" + "content='" + content + '\'' + ", frequency=" + frequency + '}';
    }
}

