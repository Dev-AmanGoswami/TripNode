# Velocity — Client

Next.js (App Router) app with two screens, built from the `driver` and `rider` designs.

| Route      | Screen | Description                                                        |
| ---------- | ------ | ------------------------------------------------------------------ |
| `/`        | Home   | Entry hub linking the two experiences.                             |
| `/driver`  | Driver | Incoming trip-request alert with a live 15s countdown, accept/decline. |
| `/rider`   | Rider  | Premium booking panel: pickup/dropoff, vehicle class, scheduling.  |

## Run

```bash
npm install
npm run dev      # http://localhost:3000
npm run build && npm start   # production
```

## Optimization techniques applied

- **Compiled Tailwind** (PostCSS) instead of the runtime CDN — design tokens live in `tailwind.config.ts`, only used classes ship.
- **`next/font`** self-hosts Inter (subset, `display: swap`) → no CDN round-trip, zero layout shift (CLS).
- **Server Components by default**; only the genuinely interactive parts (`RequestCard`, `BookingForm`) are `"use client"`, keeping JS minimal.
- **Static prerendering (SSG)** for every route → ~108 kB first-load JS, instant TTFB.
- **Per-route code splitting** + `<Link prefetch>` for instant navigation.
- **`optimizeCss`** (critical-CSS inlining via critters) and `removeConsole` in production.
- **Accessibility & UX**: `prefers-reduced-motion` honored, `role`/`aria` on the request dialog and vehicle radiogroup, decorative icons marked `aria-hidden`.
- Timer logic uses a `useReducer` state machine with proper interval/timeout cleanup (no leaks, Strict-Mode safe).

## Structure

```
app/
  layout.tsx        # fonts, metadata, icon font
  page.tsx          # home hub
  driver/page.tsx   # server shell  → <RequestCard/>
  rider/page.tsx    # server shell  → <BookingForm/>
  globals.css       # theme layers + animations
components/
  Icon.tsx          # Material Symbols wrapper
  RequestCard.tsx   # client: countdown + accept/decline
  BookingForm.tsx   # client: vehicle select + inputs
```
