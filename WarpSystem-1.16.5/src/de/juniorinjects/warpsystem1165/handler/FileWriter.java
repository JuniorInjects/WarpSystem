package de.juniorinjects.warpsystem1165.handler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

public class FileWriter {

    private File f;
    private YamlConfiguration c;

    public FileWriter(String FilePath, String FileName) {
        this.f = new File(FilePath, FileName);
        this.c = YamlConfiguration.loadConfiguration(this.f);
    }

    public boolean exist() {
        return this.f.exists();
    }

    public FileWriter setValue(String ValuePath, Object Value) {
        this.c.set(ValuePath, Value);
        return this;
    }

    public FileWriter setDefaultValue(String ValuePath, Object Value) {
        if(!valueExist(ValuePath)){
            this.c.set(ValuePath, Value);
            save();
        }
        return this;
    }

    public Object getObject(String ValuePath) {
        return this.c.get(ValuePath);
    }

    public boolean valueExist(String value) {
        return this.getObject(value) != null;
    }

    public FileWriter save() {
        try {
            this.c.save(this.f);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return this;
    }

    public boolean getBoolean(String ValuePath) {
        return this.c.getBoolean(ValuePath);
    }

    public String getString(String ValuePath) {
        return this.c.getString(ValuePath);
    }

    public Integer getInt(String ValuePath) {
        return this.c.getInt(ValuePath);
    }

    public List<String> getStringList(String ValuePath) {
        return this.c.getStringList(ValuePath);
    }

    public List<Integer> getIntList(String ValuePath) {
        return this.c.getIntegerList(ValuePath);
    }

    public Long getLong(String ValuePath) {
        return this.c.getLong(ValuePath);
    }

    public Float getFloat(String ValuePath) {
        return (float)this.c.getLong(ValuePath);
    }

    public Double getDouble(String ValuePath) {
        return this.c.getDouble(ValuePath);
    }

}