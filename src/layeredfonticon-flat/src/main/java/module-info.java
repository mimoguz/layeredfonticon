import org.jspecify.annotations.NullMarked;

@NullMarked
module io.github.mimoguz.layeredfonticon.flat {

    exports io.github.mimoguz.layeredfonticon.flat;

    requires java.desktop;
    requires org.jspecify;
    requires com.formdev.flatlaf;
    requires io.github.mimoguz.layeredfonticon;

}
