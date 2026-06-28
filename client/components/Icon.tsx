import type { CSSProperties } from "react";

type IconProps = {
  /** Material Symbols ligature name, e.g. "electric_car". */
  name: string;
  className?: string;
  /** Optical size in px; defaults to inherited font-size. */
  size?: number;
  filled?: boolean;
  weight?: number;
};

/**
 * Lightweight wrapper around the Material Symbols icon font.
 * Renders a ligature span — no per-icon SVG payload, decorative by default.
 */
export function Icon({ name, className, size, filled = false, weight = 400 }: IconProps) {
  const style: CSSProperties = {
    fontVariationSettings: `'FILL' ${filled ? 1 : 0}, 'wght' ${weight}, 'GRAD' 0, 'opsz' 24`,
    ...(size ? { fontSize: size, lineHeight: 1 } : null),
  };
  return (
    <span aria-hidden className={`material-symbols-outlined ${className ?? ""}`} style={style}>
      {name}
    </span>
  );
}
