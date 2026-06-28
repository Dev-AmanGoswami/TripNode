import Link from "next/link";
import { Icon } from "@/components/Icon";

const screens = [
  {
    href: "/rider",
    label: "Rider",
    title: "Book a Ride",
    desc: "Premium booking flow — pick a destination, choose a vehicle class, confirm.",
    icon: "person",
  },
  {
    href: "/driver",
    label: "Driver",
    title: "Incoming Requests",
    desc: "Real-time trip alerts with a live countdown to accept or decline.",
    icon: "directions_car",
  },
] as const;

export default function Home() {
  return (
    <main className="abstract-bg relative flex min-h-screen flex-col items-center justify-center overflow-hidden px-margin-mobile">
      <div className="z-10 w-full max-w-3xl text-center">
        <span className="text-label-sm font-bold uppercase tracking-[0.3em] text-primary/80">Velocity</span>
        <h1 className="mt-sm text-headline-xl font-bold tracking-tighter text-on-surface">
          Choose your experience
        </h1>
        <div className="mt-xl grid gap-md sm:grid-cols-2">
          {screens.map((s) => (
            <Link
              key={s.href}
              href={s.href}
              prefetch
              className="glass-panel group flex flex-col items-start gap-md rounded-xl border border-outline-variant/30 p-lg text-left transition-all hover:border-primary/40 hover:bg-surface-container-highest/40"
            >
              <div className="flex h-14 w-14 items-center justify-center rounded-xl border border-primary/20 bg-primary/10">
                <Icon name={s.icon} size={32} className="text-primary" />
              </div>
              <div>
                <p className="text-label-sm uppercase tracking-widest text-on-surface-variant">{s.label}</p>
                <h2 className="mt-xs text-headline-md font-semibold text-on-surface">{s.title}</h2>
                <p className="mt-xs text-body-md text-on-surface-variant">{s.desc}</p>
              </div>
              <span className="mt-auto flex items-center gap-xs text-label-md font-semibold text-primary">
                Open
                <Icon name="arrow_forward" className="transition-transform group-hover:translate-x-1" />
              </span>
            </Link>
          ))}
        </div>
      </div>
    </main>
  );
}
