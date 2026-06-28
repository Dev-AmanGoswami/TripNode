/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  poweredByHeader: false,
  // Pin tracing to this app so sibling lockfiles don't confuse the build.
  outputFileTracingRoot: import.meta.dirname,
  compiler: {
    // Strip console.* in production builds (keep errors).
    removeConsole: process.env.NODE_ENV === "production" ? { exclude: ["error"] } : false,
  },
  experimental: {
    optimizeCss: true,
  },
};

export default nextConfig;
